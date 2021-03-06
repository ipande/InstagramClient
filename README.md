# Project 1 - InstaPopular

InstaPopular is an android app that allows a user to check out popular photos from Instagram. 
The app utilizes Instagram API to display images and basic image information to the user.

Time spent: 7 hours spent in total

## User Stories

The following **required** functionality is completed:

* User can **scroll through current popular photos** from Instagram
* For each photo displayed, user can see the following details:
* Graphic, Caption, Username
* Relative timestamp, like count, user profile image

The following **optional** features are implemented:

* User can **pull-to-refresh** popular stream to get the latest popular photos
* Display each photo with the same style and proportions as the real 
Instagram
* Display each user profile image using a RoundedImageViewDisplay each 
user profile image using a [RoundedImageView](https://github com/vinc3m1/RoundedImageView)
* Display a nice default placeholder graphic for each image during loading
* Improved the user interface through styling and coloring:
	Specifically, used standardized colors to match instagram colors, heart icon for likes, centered and padded views properly.
* Used the butterknife library for annotation based faster view bindings
    
The following **additional** features are implemented:

* Improved the jittery scrolling by calculating and setting imagevView height before loading it into picasso.
* Used a custom instagram icon.

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='InstaPopular.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright 2016 Ishan Pande

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
