//
//  Mit.swift
//  CS16-effie
//
//  Created by Effie on 2023/02/27.
//

import Foundation

final class Mit {
  private static var fileManager: FileManager {
    return FileManager.default
  }
  
  private static func getContentsURL(from directoryURL: URL) throws -> [URL] {
    return try fileManager.contentsOfDirectory(
      at: directoryURL,
      includingPropertiesForKeys: nil,
      options: [.skipsHiddenFiles]
    )
  }
  
  private static func mapContents<Output: MitOutput>(
    of directoryURL: URL,
    transform: (URL) throws -> (Output)?
  ) throws -> [Output] {
    let entities = try getContentsURL(from: directoryURL)
    return try entities.compactMap(transform)
  }
  
  private static func list(_ directoryURL: URL) throws -> [ListOutput] {
    let listTransform = { (entity: URL) -> ListOutput? in
      let name = entity.lastPathComponent
      guard let size = entity.fileByteSize else { return nil }
      return ListOutput(fileName: name, info: size)
    }
    return try mapContents(of: directoryURL, transform: listTransform)
  }
  
  private static func hash(_ directoryURL: URL) throws -> [HashOutput] {
    let hashTransform = { (entity: URL) -> HashOutput? in
      let name = entity.lastPathComponent
      guard let data = entity.data else { return nil }
      let hash = data.hash
      return HashOutput(fileName: name, info: hash)
    }
    return try mapContents(of: directoryURL, transform: hashTransform)
  }
  
  private static func zlib(_ directoryURL: URL) throws -> [ListOutput] {
    let zlibTransform = { (entity: URL) throws -> ListOutput?  in
      let fileName = entity.lastPathComponent
      
      let newFileName = fileName + ".z"
      let newFileURL = directoryURL.appending(component: newFileName)
      
      guard let zlib = entity.data?.zlib else { throw MitError.CannotCompress(fileName) }
      try zlib.write(to: newFileURL)
      
      guard let zlibSize = newFileURL.fileByteSize else { return nil }
      return ListOutput(fileName: newFileName, info: zlibSize)
    }
    return try mapContents(of: directoryURL, transform: zlibTransform)
  }
  
  private static func process(_ command: MitCommand) throws -> [any MitOutput] {
    switch command {
    case .list(let path): return try list(path.fileSystemURL)
    case .hash(let path): return try hash(path.fileSystemURL)
    case .zlib(let path): return try zlib(path.fileSystemURL)
    }
  }
  
  static func run() {
    while true {
      do {
        let input = try MitIOManager.getInput()
        guard input != "q" else {
          print("terminated")
          return
        }
        
        let command = try MitCommand.getCommand(from: input)
        let outputs = try process(command)
        MitIOManager.printOutput(outputs)
      } catch {
        if let mitError = error as? MitError {
          print(mitError.actionMessage)
        } else {
          print(error.localizedDescription)
        }
      }
      
      print("")
    }
  }
}
