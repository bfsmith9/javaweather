# CHANGELOG

All notable changes to this project will be documented in this file.

The format is based on <https://keepachangelog.com/en/1.0.0/>,
and this project adheres to <https://semver.org/spec/v2.0.0.html>.

## [Unreleased]

## [javaweather-2019_1-BFS_9]
### UPDATED
- Made GetLargeRada inherit from GetRadar (probably kind of pointless...I think we may really just need the single GetRadar class for all three radar image grabs.) Testing new branch.
- Made new testing branch - see if the "9" I've added above will work in Git using tags, vs. CVS method.

## [javaweather-2019_1-BFS]
### UPDATED
- Added some comments to Javaweather.java, including new Javadoc comments.
- Added Javadoc documentation. Problem with 'javadoc error "expression expected" script type eclipse "language servers"' (that's what I searched). I corrected it in the html after generating - not sure if that's right.
- Moved to Git instead of CVS. Issues with CVS server on the Pi that proved to difficult to resolve, sadly. The documentation on these issues is very old and sketchy. I'll have to figure out how to do version numbers (tags?) and rethink my workflow.

## [javaweather-2019_1-BFS_7] - 2019-12-27
### By <bfsmith9@fastmail.net>
- Everything seems to be working now. No more data files causing CVS problems; all warnings cleared.
### Updated
- CHANGELOG.md
- FileReadingObject.java. Fixed non-static call; closed the BufferedReader object; removed unused variable.
- RegExWeatherTesting.java. Removed unused variable.
### Removed
- FileReader2.java - unneeded.

## [javaweather-2019_1-BFS_6] - 2019-12-27
### By <bfsmith9@fastmail.net>
### Updated
- Changelog
- .cvsignore
### Removed
- Seems to be working. Removing a few more data files from project to try to exclude them.

## [javaweather-2019_1-BFS_5] - 2019-12-27
### By <bfsmith9@fastmail.net>
### Updated
- Changelog
### Removed
- Removing data files from project to try to exclude them.

## [javaweather-2019_1-BFS_4] - 2019-12-27
### By <bfsmith9@fastmail.net>
### Updated
- Changelog
### Added
- .cvsignore file to exclude data files

## [javaweather-2019_1-BFS_3] - 2019-12-27
### By <bfsmith9@fastmail.net>
### Updated
- Changelog
- Removed unused variables from GetRegionalRadar.java
- New data file updates (like conditions.txt - can we exclude these?)

## [javaweather-2019_1-BFS_2] - 2019-12-27
### By <bfsmith9@fastmail.net>
### Updated
- Changelog
- Removed unused variables from GetRadar.java

## [javaweather-2019_1-BFS_1] - 2019-12-27
### By <bfsmith9@fastmail.net>
### Added
- Changelog