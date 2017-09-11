package com.prokarma.model.converters;

public interface Converter <I, O> {
  O convert(I in);
}
