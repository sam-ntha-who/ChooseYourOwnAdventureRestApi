# DIY Storytime Rest API
This is the RESTful API for DIY Storytime, our web app for the Final Project for Grand Circus Java Bootcamp. The web app can be found here: https://github.com/sam-ntha-who/ChooseYourOwnAdventureWebApp.git

# The DIY Storytime Web App

The DIY Storytime web app makes use of this API to communicate with our No-SQL database to perform all crud functionality to both Scene and Story collections so that stories can be played, written, edited and deleted with ease. In addition, it consumes other APIs (Pexels, TwinWord) to get data from scenes and generate photos to display.

# The API

This API performs all of the CRUD (Create/Read/Update/Delete) functionality via HTTP Requests from the web app. Upon initialization the API copies the stories and scenes from a master database into fully editable collections that the user can customize to their heart's desire.  It assembles the tree structure whenever something is changed via the play function, while caluclating the pathlengths and boolean for longest and shortest paths. The tree is arranged in a non-binary/multifurcating structure so that story paths can be easily nested within the scenes they are chosen from. This allows the app to be updated dynamically. 

# Choose Your Own Adventure?

An interactive book or visual novel (or web app) written from a second-person point of view that gamifies reading, with the reader assuming the role of the protagonist and making choices that determine the main character's actions and the plot's outcome. 
