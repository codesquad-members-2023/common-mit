//
//  MitCommand.swift
//  PRPractice
//
//  Created by SONG on 2023/02/27.
//

import Foundation

class MitCommand {
    
    let fileManager : FileManager
    var path = ""
    var fileList : [String] = []
    
    
    init(fileManager: FileManager) {
        self.fileManager = fileManager
    }
    
    func run() {
        firstCommand()
        arranging()
        printFileList()
    }
    
    func firstCommand () {
        let rawCommand = readLine()!
        if rawCommand.contains("list") {
            if let path = rawCommand.range(of: "\\/.*\\/", options: .regularExpression) {
                self.path = String(rawCommand[path])
            }
        }
    }
    
    
    func arranging () {
        do {
            let contents = try fileManager.contentsOfDirectory(atPath: path)
            self.fileList = contents
            
        } catch let error as NSError {
            print("\(error)")
        }
    }
    
    func printFileList () {
        for item in fileList {
            print(item)
        }
    }
    
}
