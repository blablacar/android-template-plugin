# Android Template Plugin



<!-- Plugin description -->
The aim of this plugin is to make easier the creation of the differents layer of the clean architecture Data - Domain - Presentation Layer
<!-- Plugin description end -->

<!-- TODO(you): Modify the rest of this file to describe how to use or contribute to the project. -->
## Install
  In the first step, the plugin will be installed manually : 
So to get the plugin in the first place you can clone the repository and run
```bash
./gradlew runIde
```
this command line will build the project and also launch a new Android Studio instance whithin a JVM. 
Then close the JVM and check in the `/build/libs/` you will find the jar file `plugin-template-0.0.1.jar`.
### Manual installation in Android Studio 
Once you've located the jar file open the Plugin Management windows of Android Studio. 
<img width="972" alt="image" src="https://user-images.githubusercontent.com/112870556/209342878-7ce16f59-6188-4e8f-a90a-fd93f93c4722.png">

Then click on `Install plugin from disk` and then choose `plugin-template-0.0.1.jar` in your file explorer. 
Now that you've done that, Android Studio will restart and you will be good to go. 
## Usage
As we are implementing a new `WizardProvider` you'll find all the template here : 
<img width="887" alt="Screenshot 2022-12-23 at 14 26 02" src="https://user-images.githubusercontent.com/112870556/209343591-52cfb2c9-52d1-4b8f-9c39-f83192a59f5e.png">


All the new Templates will be prefixed by BBC, because we (unfortunately :/ ) can't create our own category.


## To contribute
To explain a bit a project structure :

The `manager` package contains the `ProjectFileManager` class, this one is about finding the right place in the project where the user want to save his whether it's a ressource file, a class or a test class. The `PackageManager` class take care of the package locations.

The `templates` package is where you'll add your templates. This package is structured as it follow :
```bash
├── templates
│   ├── WizardTemplateProviderImpl.kt
│   ├── presentationlayer
│   │   ├── PresentationLayerTemplate.kt
│   │   ├── PresentationLayerTemplateRecipe.kt
│   │   └── klass
│   │       └── Presentation.kt
│   └── standalone
│       ├── StandaloneClassTemplate.kt
│       ├── StandaloneClassTemplateRecipe.kt
│       └── klass
│           ├── StandaloneActivity.kt
│           ├── StandaloneFragment.kt
│           ├── StandaloneLayout.kt
│           └── StandaloneViewModel.kt

```
So if you want to add your own template, in a new package, you'll need to :
- Create the `YourStuffTemplate` file, in this file you will create a new `template{}` this will be the UI windows that ask to the user the name he wants for his class and everything you need to create a Template Class. 
- Then create the `TemplateRecipe` file, as is name indicate, this file will contains the "recipe" of your template, so if you want to create a template that create a Fragment and a ViewModel, in your recipe you will have : 
```kotlin 
fun RecipeExecutor.fragmentPresentationLayerTemplate(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
    layoutName: String,
    viewModelName: String
) {
    val (projectData, _, _) = moduleData
    val project = projectInstance ?: return
    addAllKotlinDependencies(moduleData)
    addPackageName(packageName, projectData.applicationPackage.toString())

    val pfm = ProjectFileManager(project, moduleData, packageName)
    if (pfm.init().not()) return

    createPresentationFragment(fragmentName = className, layoutName = layoutName, viewModelName = viewModelName).saveClass(
        pfm.getPath(),
        packageName,
        className.asKt()
    )

    createStandaloneViewModel(packageName = packageName, viewModelName = viewModelName).saveClass(
        pfm.getPath(),
        packageName,
        viewModelName.asKt()
    )
}

```
- The function inside the recipe like `createStandaloneViewModel` or `createPresentationFragment` are the one that contains the template. Those function are returning the template as a `String`, this is why after we have the  `saveClass` function that will create the file in the right place and fill it with the `String` returned by `createPresentationFragment`. 

If you want to check how the `saveClass` `saveXML` or `saveTest` function are made, they are located in the `Utils` class. 

### Test your changes
As said on the top of this document, you will juste need to run 
```bash
./gradlew runIde
```
to run a new Android Studio in a JVM and then test the output of your new Template. 

