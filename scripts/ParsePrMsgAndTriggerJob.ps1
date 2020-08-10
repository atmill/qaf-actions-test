####################################
# Parse PR Message and Trigger Job #
# Author: Adin Miller              #
####################################

# Checks the correct number of arguments were given
if ($Args.Count -lt 1) {
  Write-Host "Usage: .\ParsePrMsgAndTriggerJob.ps1 <PR-message>"
  Write-Host "Parses the given PR message, which should contain arugments to pass along to a job"
  Write-Host "Example: .\ParsePrMsgAndTriggerJob.ps1 'Made changes EXECUTE AUTOMATION job=productA;group=test;env=qa;branch=qa'"
  Write-Host ""

  exit 1
}

# Obtains PR message argument
$fullMsg = [string] $Args[0]
$separator = [string[]]@("EXECUTE AUTOMATION")
$splitOpt = [System.StringSplitOptions]::RemoveEmptyEntries
$fullMsgPieces = $fullMsg.Split($separator, $splitOpt)

# If no arguments were specified in the PR message
if ($fullMsgPieces.Count -eq 1) {
  Write-Host "No arguments were specified in the PR message; failing script"
  exit 1
}

$argStr = $fullMsgPieces[1].trim()
$scriptArgs = $argStr.Split(";")

# A list of all possible script arguments
# REQUIRED
$jobArg = ""
$groupArg = ""
$envArg = ""
$branchArg = ""
# NOT REQUIRED - must have default value
$browserArg = "Chrome"

# For each given script argument
foreach ($curArg in $scriptArgs) {
  if ($curArg -notmatch "=") {
    Write-Host "= not found in current argument, $curArg; skipping to next argument"
    continue
  }

  $argPieces = $curArg.Split("=")
  $argName = $argPieces[0]
  $argValue = $argPieces[1]

  Switch -Exact ($argName) {
    "job" {
      $jobArg = $argValue
    }
    "group" {
      $groupArg = $argValue
    }
    "env" {
      $envArg = $argValue
    }
    "branch" {
      $branchArg = $argValue
    }
    "browser" {
      $browserArg = $argValue
    }
    default {
      Write-Host "Argument not recognized: $argName"
    }
  }
}

# Checks that required arguments were set
$argsSetFlag = $jobArg -ne "" -and $groupArg -ne "" -and $envArg -ne "" -and $branchArg -ne ""
if (!$argsSetFlag) {
  Write-Host "Not all required arguments were set: job=$jobArg; group=$groupArg; env=$envArg; branch=$branchArg"
  exit 1
}

# Depending on the specified job, the appropriate Jenkins endpoint is called
# to execute the job with the given arguments
Switch -Exact ($jobArg) {
  "productA" {
    # TODO call Jenkins endpoint to run productA job with given argumemts
    Write-Host("productA job: -Dtest.group=$groupArg -Dtest.env=$envArg -Dtest.branch=$branchArg -Dtest.browser=$browserArg")
  }
  "productB" {
    # TODO call Jenkins endpoint to run productB job with given argumemts
    Write-Host("productB job: -Dtest.group=$groupArg -Dtest.env=$envArg -Dtest.branch=$branchArg -Dtest.browser=$browserArg")
  }
  default {
    Write-Host "Job argument value not recognized: $jobArg"
    exit 1
  }
}