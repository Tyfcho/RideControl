package me.tyfcho.rideControl.util

/**
 * Utility object for evaluating logic expressions.
 */
object LogicEvaluator {
    /**
     * Evaluates a logic expression with the given inputs.
     *
     * @param logic The logic expression to evaluate.
     * @param inputs The inputs to use in the evaluation.
     * @return The result of the evaluation.
     */
    fun evaluate(logic: String, inputs: Map<String, Boolean>): Boolean {
        val expression = logic.replace("*", "&&").replace("+", "||").replace("!", "!")
        val engine = javax.script.ScriptEngineManager().getEngineByName("JavaScript")
        inputs.forEach { (key, value) ->
            engine.put(key, value)
        }
        return engine.eval(expression) as Boolean
    }
}