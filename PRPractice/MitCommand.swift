//
//  MitCommand.swift
//  PRPractice
//
//  Created by SONG on 2023/02/27.
//

import Foundation

class MitCommand {
    
    let fileManager = FileManager()
    var path = ""
    
    func inputPath () {
        let url = readLine()
        self.path = url ?? ""
    }
    
    
    
}
