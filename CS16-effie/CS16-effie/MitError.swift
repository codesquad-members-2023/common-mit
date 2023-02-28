//
//  MitError.swift
//  CS16-effie
//
//  Created by Effie on 2023/02/27.
//

import Foundation

enum IOError: Error {
  case InvalidInput
}

enum MitError: Error {
  case NotMit(_ command: String)
  case InvalidCommand
  case CannotFetchDirectoryInfo
  case CannotCompress
  
  var actionMessage: String {
    switch self {
    case .NotMit(let command): return "command not found: \(command)"
    case .InvalidCommand: return "존재하지 않는 mit 명령어입니다. 다시 입력해주세요."
    case .CannotFetchDirectoryInfo: return "디렉토리를 탐색할 수 없습니다."
    case .CannotCompress: return "압축할 수 없는 파일이 있습니다."
    }
  }
}
