//
//  Data+hash.swift
//  CS16-effie
//
//  Created by Effie on 2023/02/27.
//

import Foundation
import CryptoKit

extension Data {
  var hash: String {
    let hashDigest = SHA256.hash(data: self)
    return hashDigest.compactMap { String(format: "%02x", $0) }.joined()
  }
  
  var zlib: Data? {
    guard let compressed = try? (self as NSData).compressed(using: .zlib) else { return nil }
    return Data(compressed)
  }
}
