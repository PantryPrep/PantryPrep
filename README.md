# Pantry Prep

The PantryPrep application is an Android application built in Java that helps you utilize every ingredient in your pantry, ranging from fresh ingredients with a short shelf life to long-lasting spices. The application automatically sorts your added pantry items and gives them a shelf-life so that you will be warned when the ingredients are expiring.  A stretch goal is to allow users to search for suitable recipes based on their pantry ingredients, utilizing the ingredients in danger of expiring first.  

## User Stories

The following are core story functionality:

* [ ] Profile creation/login (username only) to Parse Server, data stored server side.
  * [ ] Upon profile creation, user will be asked to create their “pantry”, an inventory of what they currently have.  This includes: 
	* Ingredient
	* Cost
	* Amount + unit of measure
	* Type of ingredient (from predetermined list)
	* Expiration date override (otherwise use existing expiration dates)
	* Custom camera picture, otherwise will use default placeholder. 
* [ ] Inventory of pantry ingredients in a grid view 
* [ ] Drawer on left hand side to filter by produce type (protein, vegetables, dairy, etc).  
* [ ] Expiration dates of pantry ingredients
* [ ] Push notification for ingredients nearing expiration
* [ ] Expired food items are added to a "Wasted Food" list in a separate screen that tallies up your cost.  
* [x] Connect to Yummly’s API to retrieve recipes 
  * [ ] Recipes based on the ingredients that are going to expire soon are listed first
  * [x] Select Recipe Suggestions based on your ingredients 



The following are optional features: 

* [ ] User can share “I made this!” with completed recipe and list of ingredients
* [ ] User can tap “Wasted Food” and see list of expired ingredients
* [ ] Nutrition breakdown (calories, fat, vitamins, sodium)
* [ ] As user is typing ingredient name auto complete
* [ ] social media aspects: add photo functionality to share with facebook/twitter/
* [ ] Select recipe based on only ingredients you have
* [ ] Select recipes with 1, 2, 3, etc missing ingredients in order. 



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
