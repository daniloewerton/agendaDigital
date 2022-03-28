/* eslint-disable jsx-a11y/anchor-is-valid */

import './style.css'

function Listing_Contact() {

	const contato = {
		name: "Danilo Ewerton Pereira Freire"
	}

  return (
    <main className="container">
		<section className="container__form-listing">
			<div className="container__box-listing">
			<h1 className="container__titulo">Contatos</h1>

			<a href="#" type="button" className="botao box_botao">Adicionar</a>

			<ul className="list-group lista">
				<li className="list-group-item d-flex align-items-center justify-content-between lista__item">{contato.name}
					<div className="">
						<a type="button" className="btn btn-dark">Editar</a>
						<a type="button" className="btn btn-dark">Ver Endereços</a>
						<a type="button" className="btn btn-dark">Apagar</a>
					</div>
				</li>
				<li className="list-group-item d-flex align-items-center justify-content-between lista__item">{contato.name}
					<div className="">
						<a type="button" className="btn btn-dark">Editar</a>
						<a type="button" className="btn btn-dark">Ver Endereços</a>
						<a type="button" className="btn btn-dark">Apagar</a>
					</div>
				</li>
			</ul>
			</div>
		</section>
    </main>
  );
}

export default Listing_Contact;
