const botaoSelecionarTema = document.getElementById('botao-selecionar-tema');
const appRoot = document.getElementById('app-root');

botaoSelecionarTema.addEventListener('click', () => {
    appRoot.classList.toggle('dark-theme');
});