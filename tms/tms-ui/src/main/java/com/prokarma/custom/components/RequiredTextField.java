package com.prokarma.custom.components;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

@SuppressWarnings("rawtypes")
public class RequiredTextField extends TextField{

	private static final long serialVersionUID = 1L;

	public RequiredTextField(final String id) {
           super(id);
           setRequired(true);
   }

   @SuppressWarnings({ "unchecked" })
public RequiredTextField(final String id, final IModel model) {
           super(id, model);
           setRequired(true);
   }
}
