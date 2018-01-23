

jsonFormater <- function(vars, y) {

    variables = ""
    for (v in 1:length(vars)){
           variables = paste(variables, "\"var", v, "\":", vars[v], ",", sep = "")
    }
    paste("{",                
        variables,
        "\"y\":", y,
        "}", sep = '')
}


