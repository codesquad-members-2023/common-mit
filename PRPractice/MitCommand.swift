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
        inputPath()
        arranging()
        printFileList()
    }
    
    func inputPath () {
        let url = readLine()
        self.path = url ?? ""
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
