//
//  String+url.swift
//  CS16-effie
//
//  Created by Effie on 2023/02/28.
//

import Foundation

extension String {
  var fileSystemURL: URL {
    return URL(filePath: self)
  }
}
