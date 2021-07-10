import HelloWorld from './components/HelloWorld.vue';
import SetUpTest from './components/SetUpTest.vue';
import FormTest from './components/FormTest.vue';
import Parent from './components/Parent.vue';


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
  ]

export default routes