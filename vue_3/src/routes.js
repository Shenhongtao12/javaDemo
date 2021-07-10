import HelloWorld from './components/HelloWorld.vue';
import SetUpTest from './components/SetUpTest.vue';
import FormTest from './components/FormTest.vue';


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
  ]

export default routes