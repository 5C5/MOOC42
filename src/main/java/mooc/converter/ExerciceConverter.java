package mooc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.model.diagram.DefaultDiagramModel;

import com.google.gson.Gson;

/**
 * Converter pour le DefaultDiagramModel
 */
@FacesConverter(value = "exerciceConverter", forClass = DefaultDiagramModel.class)
public class ExerciceConverter implements Converter {

	/**
	 * Conversion de la données sous forme d'objet
	 */
	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
		Gson gson = new Gson();
		return gson.fromJson(value, DefaultDiagramModel.class);
	}

	/**
	 * Conversion de la données sous forme de String
	 */
	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
		if (value instanceof org.primefaces.model.diagram.DefaultDiagramModel) {
			Gson gson = new Gson();
			DefaultDiagramModel model = (DefaultDiagramModel) value;
			return gson.toJson(model);
		}
		return null;
	}
}
