# MusicAnalyzer - Shazam Knockoff

### MusicAnalyzer is a music identification service using an external platform to quickly process prerecorded MP3 songs, and create and tag them with their respective metadata in ID3 tag form. 

It is a very barebones minimal GUI application used only to point to a music directory. There is no sorting as sorting is to be done selectively by the user. 


#### Directions for use

In order to use MusicAnalyzer, an API key and secret is required from ACRCloud.
  1) Register at https://console.acrcloud.com/signup
  2) Under the project menu, click on "Audio and Video Recognition".
  3) Create a new project, select "Line - In Audio" and click create. 
  4) Copy your host server, access key, and access secret to the respective locations in the `Analyzer.java` file.

