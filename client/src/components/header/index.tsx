import { FunctionalComponent, h } from "preact";
import * as style from "./style.css";

const Header: FunctionalComponent = () => {
    return (
        <header class={style.header}>
            <h1>Magic Marbles</h1>
            <nav>
                <button onClick={ e => alert("restart!") }>
                    <i className="material-icons">cached</i>
                </button>
                <button onClick={ e => alert("exit!") }>
                    <i className="material-icons">clear</i>
                </button>
            </nav>
        </header>
    );
};

export default Header;
