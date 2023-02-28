//
//  main.swift
//  PRPractice
//
//  Created by SONG on 2023/02/27.
//

import Foundation

let filemanger = FileManager()
let mitCommand = MitCommand(fileManager: filemanger)
mitCommand.run()
