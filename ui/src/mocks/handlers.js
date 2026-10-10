import {http, HttpResponse} from 'msw'
import articles from '@/mocks/articlePage.json' with {type: "json"};
import articles2 from '@/mocks/articlePage2.json' with {type: "json"};

export const handlers = [
  // Intercepts requests to the backend
  http.post('/', async ({request}) => {
    return HttpResponse.json({status: "hello"});
  }),
  http.get('/actuator/health', () => {
    return HttpResponse.json({
      status: 'UP'
    })
  }),
  http.get("/api/articles", async ({request}) => {
    let page = new URL(request.url).searchParams.get("page");
    await new Promise(resolve => setTimeout(resolve, 300));
    if (!page || page === "0"){
      return HttpResponse.json(articles);
    }
    if (page === "1") {
      return HttpResponse.json(articles2);
    }
    return HttpResponse.json(articles)
  }),
]