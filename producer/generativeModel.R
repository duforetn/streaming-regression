source('jsonFormater.R')

nVars = 10
beta_min = 0
beta_max = 10

model = 'linearModel'
model.p = nVars
model.sparsity = 1
model.betas = runif(nVars, beta_min, beta_max)
model.betas = model.betas[sample(floor(nVars*min(0, model.sparsity)))] = 0
model.alpha = 0.1

linearPrediction <- function(vars)
    model.alpha + sum(model.betas*vars) 

linearGeneration <- function(sigma = 0.1) {
    vars = runif(model.p, 0, 1)   
    y = model.alpha + sum(model.betas*vars) + rnorm(1, 0, sigma)
    jsonFormater(vars, y) 
}
