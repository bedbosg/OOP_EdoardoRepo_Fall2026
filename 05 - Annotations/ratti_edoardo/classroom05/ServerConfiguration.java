package classroom05;

@Encapsulate(Visibiliy.PROTECTED)
public class ServerConfiguration {

    //getter and setter
    @GetterSetter
    public ServerConfiguration fallback;

    //ok
    private String hostname = "localhost";

    //setter only
    @GetterSetter(getter = false)
    int port = 8080;
}