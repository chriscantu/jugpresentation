<%=packageName ? "package ${packageName}\n\n" : ''%>import grails.converters.deep.*
import javax.servlet.http.*

class ${className}Controller {

    static allowedMethods = [show: "GET", update: "PUT", delete: "DELETE", save: "POST"]

    def show = {
		if (params.id) {
			if(${className}.exists(params.id)) {
				render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_OK) { ${className}.get(params.id) }
			} else {
				render(text:"No such entity", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
			}
		} else {
			params.max = Math.min(params.max ? params.int('max') : 10, 100)
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_OK) { 
				[
					total: ${className}.count(),
					list: ${className}.list(params)
				]
			}
		}
	}

	def update = {
		def ${propertyName} = ${className}.get(params.id)
		
		${propertyName}.properties = request.JSON
		
		if (${propertyName}) {
			if (${propertyName}.validate() && ${propertyName}.save()) {
				render(text:"Updated successfully", status:HttpServletResponse.SC_OK, contentType:"application/json")
			} else {
				render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_BAD_REQUEST) { ${propertyName}.errors }
			}
		} else {
			render(text:"No such entity", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
		}
	}
	
	def save = {
		def ${propertyName} = new ${className}()

		${propertyName}.properties = request.JSON

		if (${propertyName}.validate() && ${propertyName}.save()) {
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_CREATED) { ${propertyName} }
		} else {
			render(contentType:"application/json", builder:"json", status:HttpServletResponse.SC_BAD_REQUEST) { ${propertyName}.errors }
		}
	}
	
	def delete = {
		def ${propertyName} = ${className}.get(params.id)
		
		if (${propertyName}) {
			${propertyName}.delete()
			render(text:"Delete successful", status:HttpServletResponse.SC_OK, contentType:"application/json")
		} else {
			render(text:"No such entity", status:HttpServletResponse.SC_NOT_FOUND, contentType:"application/json")
		}
	}
}
