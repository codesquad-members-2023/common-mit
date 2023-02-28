//
//  URL+fileSize.swift
//  CS16-effie
//
//  Created by Effie on 2023/02/27.
//

import Foundation

extension URL {
  var fileByteSize: Int? {
    guard let infos = try? self.resourceValues(forKeys: [.fileSizeKey]) else { return nil }
    guard let sizeInByte = infos.fileSize else { return nil }
    return sizeInByte
  }
  
  var data: Data? {
    try? Data(contentsOf: self)
  }
}
