# Search and Rescue Web App
This repository contains code for our web application we developed for senior design. 

The web application consists of two main views for first responders to use: the list view and the maps view. The application was developed using the Play framework in Javascript and HTML. We utilized a Bootstrap template to create a front-end “skin” for the application, and then added to that to create the list and map views.

The first step in rendering the correct data on the web application was to calculate the location of identified survivors using flight controller data from the drone. During flight, we pushed flight controller data from the drone, such as latitude, longitude, pitch, and altitude, to MongoDB 30 times a second. We had to make sure that we were getting the correct flight controller data for a specific survivor to calculate their location accurately. To do this, we used the timestamps sent with the drone footage and the flight controller data. For a specific survivor, we used the timestamp of that image and found the latest flight controller timestamp less than the image’s and pulled that flight controller data to use in our location calculation algorithm.

The list view consists of a list of survivors detected by the YOLO algorithm. When a first responder clicks on a survivor, a popup will appear with an image of the survivor with a box around them, and their GPS coordinates, which were calculated using the location algorithm. We stored this information using MongoDB. First, we took the bounded box image from YOLO and uploaded it to AWS, which returned a URL to the image. We packaged this URL with the location calculation and uploaded this as a package to MongoDB. The list view is shown below.

![alt text](https://github.com/varunsridhar1/SearchAndRescueWebApp/blob/master/ListView.png "List view")

For the map view, we displayed a map on the page through the MapBox GL Javascript library. The map is centered around where the drone flew in that particular run. On the map, we posted markers at the calculated locations of each of the survivors identified in YOLO. When a first responder clicks on one of the markers, they will see a similar popup to the one described above, containing an image of the survivor from the drone footage and their GPS coordinates. The map view page is displayed below.

![alt text](https://github.com/varunsridhar1/SearchAndRescueWebApp/blob/master/MapView.png "Map view")
