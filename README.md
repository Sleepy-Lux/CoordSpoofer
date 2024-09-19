## Coord spoofer
![](https://img.shields.io/github/v/release/sleepy-lux/coordspoofer)<br>
❓ Easily spoof your coordinates for your livestream/videos

### Features
- Anti-Tracking
  > Spoofs your X, Y and Z coordinates on the F3 menu
- Consistency
  > 2 clients with same seed and build version will see the same coordinates
- Commands
  > `setCoordSeed` - Set the coordinate spoofer seed to a new seed
  > 
  > `getCoordSeed` - Get the current coordinate spoofer seed
  > 
  > `toggleCoordSpoofer` - Enable/Disabled the spoofer functionality
  
### Why is source not working?
 The math equation used for coordinate modification is provided by the provider when you build from source you have to change the `MathSecret.java.delete` to `MathSecret.java`
 
> Official versions with have an equation provided by me, This will be kept a secret

<a href='https://ko-fi.com/sleepylux' target='_blank'><img src='https://storage.ko-fi.com/cdn/kofi5.png' alt='Buy Me a Coffee at ko-fi.com' /></a>
