## Hello Component World
Probably the simplest example of a component. 
<BR>

Bellow code shows how to subclass the Component class to make your own component.
There is only one function that needs to be implemented.
<BR>

```kotlin
@Composable
override fun Content(modifier: Modifier)
```

<BR>
Here we create our HelloWorldState state class, it is hosted in the component scope. As long as the
component is referenced from a parent component, the component scope is alive. It will receive
the start()/stop() function calls whenever the component content appears or disappears in the 
screen. A parent navigation component usually handles the start()/stop() calls on its children and
it also considers Activity(start/stop) or iOS(viewWillAppear/viewWillDisappear) or
Window(minimized/restored) events. All these events are translated to component start/stop call
respectively.
The way you render a component is by using a *ComponentRender* Composable function that exist in 
each platform. In Desktop for example you use the DesktopComponentRender Composable. 
See other platforms bellow

<BR>

```kotlin
class HelloWorldComponent : Component() {

    private val helloWorldState = HelloWorldState()

    @Composable
    override fun Content(modifier: Modifier) {
        BackPressHandler(
            component = this,
            onBackPressed = { handleBackPressed() }
        )
        HelloWorldView(helloWorldState)
    }

}

// Desktop Example in desktopApp/src/jvmMain
fun main() =
    singleWindowApplication(
        title = "Chat",
        state = WindowState(size = DpSize(500.dp, 800.dp))
    ) {
        MaterialTheme {
            DesktopComponentRender(
                rootComponent = HelloWorldComponent(),
                onBackPressEvent = {
                    println("Back pressed event reached root node.")
                    //exitProcess(0) 
                }
            )
        }
    }

// Android Example in androidApp/src/androidMain
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComponentRender(
                rootComponent = HelloWorldComponent(),
                onBackPressEvent = { finishAffinity() }
            )
        }
    }
}

// Web Example in jsApp/src/jsMain
fun main() {
    onWasmReady {
        BrowserViewportWindow("Hello World") {
            MaterialTheme {
                BrowserComponentRender(
                    rootComponent = HelloWorldComponent(),
                    onBackPressEvent = {
                        println("Back pressed event reached root node.")
                    }
                )
            }
        }
    }
}
```

<BR>
iOS code is in swift
<BR>

```swift
// iOS Example in iosApp/iosApp
@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)

        let helloWorldComponent = Main_iosKt.buildHelloWorldComponent()

        let mainViewController = Main_iosKt.ComponentRenderer(
                rootComponent: helloWorldComponent,
                appName: "Hello World"
        )

        window?.rootViewController = mainViewController
        window?.makeKeyAndVisible()
        return true
    }
}
```

### Hello World

[Hello World](https://github.com/pablichjenkov/templato/tree/master/hello-world) is a simple example to get started with a components creation and configuration.

<table style="border:1px solid black;">
 <tr>
    <td style="border:1px solid black;"><b style="font-size:30px">Android</b></td>
    <td style="border:1px solid black;"><b style="font-size:30px">iOS</b></td>
 </tr>
 <tr>
    <td style="border:1px solid black;"><img title="Hello World Android" src="https://user-images.githubusercontent.com/5303301/215320502-0c771b1c-b5df-4181-aba0-7476d22e5995.jpg" alt="hello-world-android" width="300"></td>
    <td style="border:1px solid black;"><img title="Hello World iOS" src="https://user-images.githubusercontent.com/5303301/214742102-878b386e-e324-433f-aee9-9c5629500ccc.jpg" alt="hello-world-ios" width="300"></td>
 </tr>
</table>
<table style="border:1px solid black;">
 <tr>
    <td style="border:1px solid black;"><b style="font-size:30px">Web</b></td>
 </tr>
 <tr>
    <td style="border:1px solid black;"><img title="Hello World Web" src="https://user-images.githubusercontent.com/5303301/214742259-f912843a-cf85-4ce7-b69d-74b301eca6e5.jpg" alt="hello-world-web" width="800"></td>
 </tr>
</table>

---
