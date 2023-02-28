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
  
  private static func getEntitiesURL(from path: String) throws -> [URL] {
    let directoryURL = URL(filePath: path)
    return try fileManager.contentsOfDirectory(
      at: directoryURL,
      includingPropertiesForKeys: nil,
      options: [.skipsHiddenFiles]
    )
  }
  
  private static func list(_ path: String) -> [ListOutput] {
    var outputs: [ListOutput] = []
    do {
      let entities = try getEntitiesURL(from: path)
      outputs = entities.compactMap { entity in
        let name = entity.lastPathComponent
        guard let size = entity.fileByteSize else { return nil }
        return ListOutput(fileName: name, info: size)
      }
    } catch {
      print(error)
    }
    return outputs
  }
  
  private static func hash(_ path: String) -> [HashOutput] {
    var outputs: [HashOutput] = []
    do {
      let entities = try getEntitiesURL(from: path)
      outputs = entities.compactMap { entity -> HashOutput? in
        let name = entity.lastPathComponent
        guard let data = entity.data else { return nil }
        let hash = data.hash
        return HashOutput(fileName: name, info: hash)
      }
    } catch {
      print(error)
    }
    return outputs
  }
  
  private static func zlib(_ path: String) -> [ListOutput] {
    let pathURL = URL(filePath: path)
    var outputs: [ListOutput] = []
    
    do {
      let entities = try getEntitiesURL(from: path)
      outputs = try entities.compactMap { (entity: URL) -> ListOutput? in
        let fileName = entity.lastPathComponent
        
        let newFileName = fileName + ".z"
        let newFileURL = pathURL.appending(component: newFileName)
        
        guard let zlib = entity.data?.zlib else { throw MitError.CannotCompress }
        try zlib.write(to: newFileURL)
        
        guard let zlibSize = newFileURL.fileByteSize else { return nil }
        return ListOutput(fileName: newFileName, info: zlibSize)
      }
    } catch {
      print(error)
    }
    return outputs
  }
  
  private static func process(_ command: MitCommand) -> [any MitOutput] {
    switch command {
    case .list(let path): return list(path)
    case .hash(let path): return hash(path)
    case .zlib(let path): return zlib(path)
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
        let outputs = process(command)
        MitIOManager.printOutput(outputs)
        
      } catch {
        if let mitError = error as? MitError {
          print(mitError.actionMessage)
        } else {
          print(error)
        }
      }
      
      print("")
    }
  }
}
