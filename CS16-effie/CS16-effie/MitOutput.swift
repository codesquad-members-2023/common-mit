//
//  MitOutput.swift
//  CS16-effie
//
//  Created by Effie on 2023/02/27.
//

import Foundation

protocol MitOutput: CustomStringConvertible {
  associatedtype InfoType
  
  var fileName: String { get }
  var info: InfoType { get }
}

struct ListOutput: MitOutput {
  typealias InfoType = Int
  
  var fileName: String
  var info: Int
  
  var description: String {
    "\(fileName) \(info)B"
  }
}

struct HashOutput: MitOutput {
  typealias InfoType = String
  
  var fileName: String
  var info: String
  var description: String {
    "\(fileName) = \(info)"
  }
}
