// app.js
// console.log('app.js');

// js -> module
// 1. default export / import
// 2. named export / import

import foo from './foo.js';
import {name as 이름, n, arr, f} from './bar.js'
foo();

// console.log(name);
console.log(n)
console.log(arr);
console.log(f)

console.log(이름)