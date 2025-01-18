# taschenfahrrad

Maintaining the contents of dastaschenfahrrad.com

## Prerequisite

- Works only on a linux computer. Uses bash, rm, ...
- You must have installe imagemaick. Uses mogrif to prepare the images

To generate an update of the hompage 
 - Edit Data.scala
 - Add/remove Images in src/main/web/common/images
 - call sbt -> run gen-homepage

For mor details try sbt -> run --help
