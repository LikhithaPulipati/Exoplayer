# Exoplayer
The Project mainly deals with ExoPlayer in  which the  standard audio and video components are built on Android’s Media Codec API.


**ExoPlayer**

**Activity lifecycle**
    we'll integrate ExoPlayer properly into the activity lifecycle to support proper app back-/
    foregrounding and playback resumption in single or multi-window environment/mode.
    
    **UI components** 
    
      we'll use standard ExoPlayer UI components and customize such components and the activity to fit to our needs in different APKs 
      like for mobile, Android Instant Apps.
      
      
      **Dependencies Required**
                implementation 'com.google.android.exoplayer:exoplayer-core:2.7.3'
                implementation 'com.google.android.exoplayer:exoplayer-dash:2.7.3'
                implementation 'com.google.android.exoplayer:exoplayer-ui:2.7.3'
                
               
ExoPlayer supports features like Dynamic adaptive streaming over HTTP (DASH), SmoothStreaming and Common Encryption,
which are not supported by MediaPlayer. It's designed to be easy to customize and extend.               
