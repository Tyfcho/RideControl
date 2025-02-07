### TODO List

#### Github page:
- [ ] Create a nice README.md
- [X] Add a license
- [ ] Add a contributing guide
- [ ] Add a code of conduct
- [ ] Add a scope of the project

#### Requirements
1. **Read YAML Configuration File**:
    - The name of the ride will be the name of the YAML file.
    - YAML structure:
      ```
      RideName
      RideName.circuit1.id = 1
      RideName.circuit1.name = Test
      RideName.circuit1.io.inputs = Test_Input1, Test_Input2, Test_Input3
      RideName.circuit1.io.outputs = Test_Output1
      RideName.circuit1.io.logic = Test_Input1 * Test_Input2 + !Test_Input3
      ```

2. **Commands**:
    - `/ridecontrol create ride <attractionName> <amount of circuits>`
    - `/ridecontrol create circuit <attractionName> <amount to add to existing circuits>`
    - `/ridecontrol edit circuit <attractionName> rename <newCircuitName>`

3. **Logic Evaluation**:
    - Implement logic evaluation for circuits using `*` for AND, `+` for OR, and `!` for NOT.

#### Accomplished Today
1. **Data Classes**:
    - `CircuitData` and `RideData` classes created in `src/main/kotlin/me/tyfcho/rideControl/data`.

2. **YAML Parsing**:
    - `RideConfig` object created in `src/main/kotlin/me/tyfcho/rideControl/util` to load and save rides from/to YAML files.

3. **Logic Evaluation**:
    - `LogicEvaluator` object created in `src/main/kotlin/me/tyfcho/rideControl/util` to evaluate logic expressions.

4. **Command Framework Setup**:
    - Dependencies for Cloud command framework and BKCommonLib added to `build.gradle.kts`.
    - `CommandRegistration` class created to register commands using Cloud.

#### Current Status
- **Data Classes**: Implemented and functional.
- **YAML Parsing**: Implemented and functional.
- **Logic Evaluation**: Implemented and functional.
- **Command Registration**: Partially implemented, but contains errors.

### Next Steps
1. **Fix Command Registration Errors**:
    - Debug and correct the `CommandRegistration` class.
    - Ensure commands are properly registered and functional.

2. **Implement Command Logic**:
    - Complete the logic for creating rides, adding circuits, and renaming circuits.

3. **Testing**:
    - Test the implemented features to ensure they work as expected.

### Codebase Status
- `src/main/kotlin/me/tyfcho/rideControl/RideControl.kt`:
  ```kotlin
  package me.tyfcho.rideControl

  import org.bukkit.plugin.java.JavaPlugin

  class RideControl : JavaPlugin() {

      override fun onEnable() {
          // Plugin startup logic
      }

      override fun onDisable() {
          // Plugin shutdown logic
      }
  }
  ```

- `src/main/kotlin/me/tyfcho/rideControl/data/CircuitData.kt`:
  ```kotlin
  package me.tyfcho.rideControl.data

  data class CircuitData(
      val id: Int,
      val name: String,
      val inputs: List<String>,
      val outputs: List<String>,
      val logic: String
  )
  ```

- `src/main/kotlin/me/tyfcho/rideControl/data/RideData.kt`:
  ```kotlin
  package me.tyfcho.rideControl.data

  data class RideData(
      val name: String,
      val circuits: MutableList<CircuitData>
  )
  ```

- `src/main/kotlin/me/tyfcho/rideControl/util/RideConfig.kt`:
  ```kotlin
  package me.tyfcho.rideControl.util

  import me.tyfcho.rideControl.data.CircuitData
  import me.tyfcho.rideControl.data.RideData
  import org.yaml.snakeyaml.Yaml
  import java.io.File

  object RideConfig {
      private val yaml = Yaml()

      fun loadRide(file: File): RideData {
          val data = yaml.load<Map<String, Any>>(file.readText())
          val name = data["name"] as String
          val circuits = (data["circuits"] as List<Map<String, Any>>).map {
              CircuitData(
                  it["id"] as Int,
                  it["name"] as String,
                  it["inputs"] as List<String>,
                  it["outputs"] as List<String>,
                  it["logic"] as String
              )
          }.toMutableList()
          return RideData(name, circuits)
      }

      fun saveRide(file: File, ride: RideData) {
          val data = mapOf(
              "name" to ride.name,
              "circuits" to ride.circuits.map {
                  mapOf(
                      "id" to it.id,
                      "name" to it.name,
                      "inputs" to it.inputs,
                      "outputs" to it.outputs,
                      "logic" to it.logic
                  )
              }
          )
          file.writeText(yaml.dump(data))
      }
  }
  ```

- `src/main/kotlin/me/tyfcho/rideControl/util/LogicEvaluator.kt`:
  ```kotlin
  package me.tyfcho.rideControl.util

  object LogicEvaluator {
      fun evaluate(logic: String, inputs: Map<String, Boolean>): Boolean {
          val expression = logic.replace("!", "not")
              .replace("*", "and")
              .replace("+", "or")
          val engine = javax.script.ScriptEngineManager().getEngineByName("js")
          inputs.forEach { (key, value) ->
              engine.put(key, value)
          }
          return engine.eval(expression) as Boolean
      }
  }
  ```

- `build.gradle.kts`:
  ```kotlin
  plugins {
      kotlin("jvm") version "2.1.20-Beta2"
      id("com.github.johnrengelman.shadow") version "8.1.1"
  }

  group = "me.tyfcho"
  version = "1.0-SNAPSHOT"

  repositories {
      mavenCentral()
      maven("https://repo.papermc.io/repository/maven-public/") {
          name = "papermc-repo"
      }
      maven("https://oss.sonatype.org/content/groups/public/") {
          name = "sonatype"
      }
      maven("https://ci.mg-dev.eu/plugin/repository/everything/") {
          name = "mg-dev"
      }
  }

  dependencies {
      compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
      implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
      implementation("com.bergerkiller.bukkit:TCCoasters:1.21.4-v1-SNAPSHOT")
      implementation("org.yaml:snakeyaml:1.30")
      implementation("com.bergerkiller.bukkit:BKCommonLib:1.21.4-v1-SNAPSHOT")
      implementation("cloud.commandframework:cloud-bukkit:1.5.0")
      implementation("cloud.commandframework:cloud-kotlin-extensions:1.5.0")
  }

  val targetJavaVersion = 21
  kotlin {
      jvmToolchain(targetJavaVersion)
  }

  tasks.build {
      dependsOn("shadowJar")
  }

  tasks.processResources {
      val props = mapOf("version" to version)
      inputs.properties(props)
      filteringCharset = "UTF-8"
      filesMatching("plugin.yml") {
          expand(props)
      }
  }
  ```