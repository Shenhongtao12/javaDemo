import HelloWorld from './components/HelloWorld.vue';
import SetUpTest from './components/SetUpTest.vue';
import FormTest from './components/FormTest.vue';
import Parent from './components/Parent.vue';
import ImageVerificationCode from './components/ImageVerificationCode.vue';
import Code from './components/Code.vue';


const routes = [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
        path: '/setup',
        name: 'SetUpTest',
        component: SetUpTest
    },
    {
    path: '/form',
    name: 'FormTest',
    component: FormTest
    },
    {
    path: '/parent',
    name: 'Parent',
    component: Parent
    },
    {
      path: '/image',
      name: 'ImageVerificationCode',
      component: ImageVerificationCode
      },
      {
        path: '/code',
        name: 'Code',
        component: Code
        },
  ]

export default routes