package com.lessons.firebase.quotes.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

@Singleton
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
