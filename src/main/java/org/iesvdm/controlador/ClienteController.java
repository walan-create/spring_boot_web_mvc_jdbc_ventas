package org.iesvdm.controlador;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.iesvdm.dto.ComercialDTO2;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

//Se puede fijar ruta base de las peticiones de este controlador.
//Los mappings de los métodos tendrían este valor /clientes como
//prefijo.
//@RequestMapping("/clientes")
@Controller
public class ClienteController {

	private ClienteService clienteService;

	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired
	//@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/clientes") //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa
	public String listar(Model model) {

		List<Cliente> listaClientes =  clienteService.listAll();
		model.addAttribute("listaClientes", listaClientes);

		return "clientes";

	}

	@GetMapping("/clientes/{id}")
	public String detalle(Model model, @PathVariable Integer id) {

		Cliente cliente = clienteService.one(id);
		model.addAttribute("cliente", cliente);

		List<ComercialDTO2> comercialDTOList = clienteService.getComercialesDTOAsociados(id);
		model.addAttribute("comerciales",comercialDTOList);
		System.out.println("Comerciales encontrados: " + comercialDTOList.size()); // 🔎 Ver cuántos hay
		for (ComercialDTO2 c : comercialDTOList) {
			System.out.println("Nombre: " + c.getNombre());
		}

		return "detalle-cliente";
	}

	@GetMapping("/clientes/crear") //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa
	public String crear(@ModelAttribute ("cliente") Cliente cliente, Model model) {

		return "crear-cliente";
	}

	@PostMapping("/clientes/crear")
	public String submitCrear(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "crear-cliente";
		}
		clienteService.newCliente(cliente);
		return "redirect:/clientes";
	}

	@GetMapping("/clientes/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {

		Cliente cliente = clienteService.one(id);
		model.addAttribute("cliente", cliente);

		return "editar-cliente";

	}

	@PostMapping("/clientes/editar/{id}")
	public String submitEditar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "editar-cliente";
		}
		clienteService.replaceCliente(cliente);
		return "redirect:/clientes";
	}

	@PostMapping("/clientes/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable Integer id) {

		clienteService.deleteCliente(id);

		return new RedirectView("/clientes");
	}

}
