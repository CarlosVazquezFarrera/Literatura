package com.aluraliteratura.aluraliteratura.interfaces;

public interface IConvert {
    <T> T convert(String json, Class<T> tClass);
}
