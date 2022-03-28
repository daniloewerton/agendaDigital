import "./style.css";
import "./media.css";

function MainContainer() {
  return (
    <main className="container container-adjust">
      <section className="container__form">
        <form className="container__box" action="" method="post">
          <h1 className="container__titulo">Entrar</h1>
            <label className="form_label" htmlFor="exampleInputEmail">Email</label>
            <input type="email" className="form_input" id="exampleInputEmail" placeholder="Seu email" />
            <label className="form_label" id="label-senha" htmlFor="senha">Senha</label>
            <input type="password" className="form_input" id="senha" placeholder="Senha" name="senha" />
          <div className="box_botao">
            <button type="submit" className="botao">Login</button>
            <a href="tela_Registro.html" className="botao botao_link"> Cadastrar</a>
          </div>
          <p className="assinatura">© 2022 - DW Tecnologia.</p>
        </form>
      </section>
    </main>
  );
}

export default MainContainer;