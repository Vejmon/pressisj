import './assets/main.css'

import {createApp, markRaw} from 'vue'
import {createPinia} from 'pinia'

export async function prepareApp() {
    if (import.meta.env.DEV) {
        document.getElementById('title').innerHTML = "Vite Kuntstnern";
        const { worker } = await import('./mocks/browser')
        // start() returns a Promise that resolves when the worker is ready
        await worker.start({
            onUnhandledRequest: 'bypass', // Avoid noise in the console
        })
    }
}

// Boot the app after prepareApp finishes
export async function bootstrap(app, router){
    const pinia = createPinia()
    await prepareApp()
    createApp(app)
        .use(router)
        .use(pinia)
        .mount('#app')
}