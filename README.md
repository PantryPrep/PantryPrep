# Pantry Prep

The PantryPrep application is an Android application built in Java that helps you utilize every ingredient in your pantry, ranging from fresh ingredients with a short shelf life to long-lasting spices. The application automatically sorts your added pantry items and gives them a shelf-life so that you will be warned when the ingredients are expiring.  A stretch goal is to allow users to search for suitable recipes based on their pantry ingredients, utilizing the ingredients in danger of expiring first.  

## User Stories

The following are core story functionality:

 * [ ] User will need to create their “pantry”, an inventory of what they currently have.  This includes: 
	* Ingredient
	* Cost
	* Amount + unit of measure
	* Type of ingredient (from predetermined list)
	* Expiration date override (otherwise use existing expiration dates)
	* Custom camera picture, otherwise will use default placeholder. 

* [x] SQL Lite server will store data locally.  
* [X] Inventory of pantry ingredients in a list 
* [x] Drawer on left hand side to select ingredients or recipes. 
  * [ ] Filter your inventory by produce type (protein, vegetables, dairy, etc).  
* [ ] Expiration dates of pantry ingredients
* [ ] Push notification for ingredients nearing expiration
* [x] Connect to Yummly’s API to retrieve recipes 
  * [x] Select Recipe Suggestions based on your ingredients 
  * [x] View recipe source in a WebView inside the client. 
* [X] Dialog Alert for Adding Ingredient


The following are optional features: 

* [ ] User can share “I made this!” with completed recipe and list of ingredients
* [ ] User can tap “Wasted Food” and see list of expired ingredients
* [X] Nutrition breakdown (calories, fat, vitamins, sodium)
* [ ] As user is typing ingredient name auto complete
* [ ] social media aspects: add photo functionality to share with facebook/twitter/
* [X] Select recipe based on only ingredients you have
* [ ] Expired food items are added to a "Wasted Food" list in a separate screen that tallies up your cost.  



## Video Walkthrough

Here's a walkthrough of implemented user stories:


GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes



## Open-source libraries used



## License

    Copyright [2016] [Steve Chou, Sonny Rodriguez]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
