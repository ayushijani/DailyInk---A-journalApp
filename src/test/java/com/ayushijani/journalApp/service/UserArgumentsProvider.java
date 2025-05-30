package com.ayushijani.journalApp.service;

import com.ayushijani.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream.of(

            Arguments.of(User.builder().userName("ram").password("ram").build()),
            Arguments.of(User.builder().userName("Sooraj").password("").build())

            );
    }
}