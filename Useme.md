Commands Supported :

QUIT 

q, quit :

Quit the image processor and exit the program.

Conditions : 

Can be called at any time as long as the program is not currently running a different command.

Examples :

"q", "quit", "Q", "QuIT", "load images/koala.ppm koala q"(load then quit).

LOAD

load filepath imageName : 

Load the given image at the filepath to the ImageProcessor with the given provided name.

Conditions : 

Can be called at any time as long as the program is not currently running a different command.
Must provide two string arguments after load command.
If the file cannot be found, a message stating that the file cannot be found will be provided.

Examples : 

"load images/koala.ppm koala", "load images/sailboat.png sailboat", "load images/koala.jpeg koala", 
"load images/koala.jpg koala", "load images/koala.bmp koala"

BRIGHTEN

brighten increment imageName newImageName :

Brightens the given image by the given increment and saves it as the new given name.

Conditions : 

Can be called at any time as long as the program is not currently running a different command.
Must be provided 1 integer argument and two string arguments after brighten command.
If increment is negative, darken instead of brighten.
Brightening and darkening is capped to the values 255 and 0 respectively.
If the newImageName is the same as the old imageName, overwrite the old image.
If the image cannot be found, a message stating that the image cannot be found will be provided.

Examples : 

"brighten 10 koala koala-brighten", "brighten -20 koala-brighten koala-darken", "brighten 1 koala koala"

VERTICAL-FLIP

vertical-flip imageName newImageName :

Vertically flips the given image and saves it as the new given image name.

Conditions :

Can be called at any time as long as the program is not currently running a different command.
Must be provided two string arguments after vertical-flip command.
If the newImageName is the same as the old imageName, overwrite the old image.
If the image cannot be found, a message stating that the image cannot be found will be provided.

Examples : 

"vertical-flip koala koala-vertical", "vertical-flip koala-vertical koala-vertical"

HORIZONTAL-FLIP

horizontal-flip imageName newImageName :

Horizontally flips the given image and saves it as the new given image name.

Conditions :

Can be called at any time as long as the program is not currently running a different command.
Must be provided two string arguments after horizontal-flip command.
If the newImageName is the same as the old imageName, overwrite the old image.
If the image cannot be found, a message stating that the image cannot be found will be provided.

Examples :

"horizontal-flip koala koala-horizontal", "horizontal-flip koala-horizontal koala-horizontal"

GREY-SCALE 

grey-scale component imageName newImageName :

Turn the given image into a grey-scaled form using the provided component and save it as the new given image name.

Conditions :

Can be called at any time as long as the program is not currently running a different command.
Must be provided three string arguments after grey-scale command.
The first string argument must be one of "red", "green", "blue", "intensity", "luma", or "value".
If it is not one of those strings, then the given image will not be grey-scaled, and a message will be written accordingly.
If the newImageName is the same as the old imageName, overwrite the old image.
If the image cannot be found, a message stating that the image cannot be found will be provided.

Examples :

"grey-scale red koala koala-red", "grey-scale green koala koala", "grey-scale blue koala koala-blue",
"grey-scale luma koala koala-luma", "grey-scale intensity koala koala-intensity", "grey-scale value koala koala-value"

SAVE

save filepath imageName :

Save the given image to the given filepath.

Conditions :

Can be called at any time as long as the program is not currently running a different command.
Must be provided two string arguments after save command.
The filepath must be a supported image file(ppm, jpeg, jpg, png, bmp).
Otherwise, a message will be written in the view stating the filepath is invalid.

Examples :

"save images/koala.png koala", "save images/koala.ppm koala", "save images/koala-brighten.jpg koala-brighten", 
"save images/koala.bmp koala".

BLUR

blur imageName newImageName : 

Blur the given image and save it as the new image name.

Conditions :

Can be called at any time as long as the program is not currently running a different command.
Must be provided two string arguments after blur command.
If the newImageName is the same as the old imageName, overwrite the old image.
If the image cannot be found, a message stating that the image cannot be found will be provided.

Examples : 

"blur koala koala-blur", "blur koala-blur koala-blur"

SHARPEN

sharpen imageName newImageName :

Sharpen the given image and save it as the new image name.

Conditions :

Can be called at any time as long as the program is not currently running a different command.
Must be provided two string arguments after sharpen command.
If the newImageName is the same as the old imageName, overwrite the old image.
If the image cannot be found, a message stating that the image cannot be found will be provided.

Examples :

"sharpen koala koala-sharpen", "sharpen koala-sharpen koala-sharpen"

SEPIA

sepia imageName newImageName :

Sepia the given image and save it as the new image name.

Conditions :

Can be called at any time as long as the program is not currently running a different command.
Must be provided two string arguments after sepia command.
If the newImageName is the same as the old imageName, overwrite the old image.
If the image cannot be found, a message stating that the image cannot be found will be provided.

Examples :

"sepia koala koala-sepia", "sepia koala-sepia koala-sepia"

INVALID COMMAND

If an invalid command is entered, the view will return a message stating that the command is invalid and wait for a new valid command.




