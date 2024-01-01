const botaoSelecionarTema = document.getElementById('botao-selecionar-tema');
const html = document.querySelector('html');

botaoSelecionarTema.addEventListener('click', () => {
    html.classList.toggle('dark-theme');
});