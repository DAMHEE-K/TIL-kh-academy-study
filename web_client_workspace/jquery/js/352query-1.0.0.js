/**
 * -352Query
 * - 1.0.0
 */
const $ = (selector) => {
    const obj = new _352Query();
    const elems = Array.from(document.querySelectorAll(selector));
    elems.forEach((elem, index) => {
        obj[index] = elem;
    });
    obj.length = elems.length;
    return obj;
}

// 프로토 타입에 메소드 작성
class _352Query {
    css(name, value) {
        // this : _352Query 객체
        for (let i = 0; i < this.length; i++) {
            this[i].style[name] = value;
            return this;
        }
    }
    html(html) {
        // getter
        if (!html)
            return this[0].innerHTML;

        // setter
        for (let i = 0; i < this.length; i++)
            this[i].innerHTML = html;
        return this;
    }
    click() {

    }
    prop() {

    }
    filter(selector) {

    }
    eq(index) {

    }
    hover() {

    }
    
}