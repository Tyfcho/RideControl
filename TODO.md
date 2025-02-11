### TODO List

#### Github page:
- [ ] Create a nice README.md
- [X] Add a license
- [ ] Add a contributing guide
- [X] Add a code of conduct
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
    - `/ridecontrol panel ride <attractionName>`
    - `/ridecontrol status ride <attractionName> <open/closed/maintenance>`

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