# Run with IntelliJ IDEA

0. Configuration Project Structure  
  0-1. Set **Project SDK** to `1.8` and **Proejct language level** to `8`.  
  0-2. Set **Project compiler output** to `<your workspace directory>/out` (e.g. */workspace/robocode/out*)  
  
1. Add new Run Configuration as **Application** [#](http://robowiki.net/wiki/Robocode/Running_from_Eclipse)  
  1-1. **Main Class** : `robocode.Robocode`  
  1-2. **VM Options** : `-Xmx512M -Dsun.io.useCanonCaches=false -Ddebug=true`  
  1-3. **Working Directory** : `.robocode`  
  
2. Adding your Robot Project to Robocode [#](http://robowiki.net/wiki/Robocode/Add_a_Robot_Project)  
2-1. Open Robocode **Preferences** and select the **Development Options** tab  
2-2. Add the path to your project output directory (e.g. */workspace/robocode/out/production/robocode*)  
  

# Guide
* [Robocode API](http://robocode.sourceforge.net/docs/robocode/)
* [Tutorial](http://robowiki.net/wiki/Robocode/My_First_Robot)