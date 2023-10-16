### Info 

Example of video streaming service with Akka Streams

Full article is available on:
1. [Dzone](https://dzone.com/articles/hello-world-in-akka)
2. [Medium](https://medium.com/@PaskSoftware/video-streaming-with-akka-streams-15b29c72a5bf)

### **Running locally**

Before starting doing any changes I recommend to import project into IDE and then perform any changes from there.

To successfully run application locally you have to change first line in `stream` method inside `Streamer` object 
and change value of `path` variable. I have added small `.mp4` file to repository, so you can use it as test video.

Example
<br>
`val path = "path/to/file"` => `val path = "/path/to/project/test-video.mp4"`
