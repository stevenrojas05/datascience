#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Dec 11 06:57:09 2019

@author: henry
"""

from ecommerce_connection import EcommercePostgres
import turicreate as tc

ecommerce_postgres = EcommercePostgres()
ratings = ecommerce_postgres.getDataframe()

# Preprocesamiento de datos con turicreate, convierte la entrada proporcionada por la base de datos en un Frame
data = tc.SFrame(ratings)

print(data)

# Entrenamiento del modelo
training_data, validation_data = tc.recommender.util.random_split_by_user(
    data, 'item_id', 'user_id')

model = tc.recommender.create(
    training_data, 'user_id', 'item_id', target='rating')

model.save("my_model.model")

