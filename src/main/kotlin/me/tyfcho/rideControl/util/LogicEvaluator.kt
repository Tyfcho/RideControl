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