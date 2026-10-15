import ArticlePreview from '@/components/ArticlePreview.vue';

export default {
  title: 'Components/ArticlePreview',
  component: ArticlePreview,
  tags: ['autodocs'],
  argTypes: {
    title: {
      control: 'text',
      description: 'title for the header',
    },
    subtitle: {
        control: 'text',
        description: 'subtitle for the header',
    },
    text: {
        control: 'text',
        description: 'text for the body',
    },
    link: {
        control: 'text',
        description: 'link for the article',
    }
  }
};

export const Default = {
  args: {
      "id": "d8c797c5-f06e-4b08-9570-b713ea9e2ed2",
      "title": "Enda en tittel",
      "subtitle": "wow en liten forklaring!",
      "text": "Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...Her kommer det masse informasjon! igjen og igjen...",
      "created": "2026-10-15T19:52:18.037+00:00",
      "links": [
          {
              "name": "mitt show!",
              "href": "/url",
              "faIcon": "faLink"
          },
          {
              "name": "min Facebook side!",
              "href": "/url",
              "faIcon": "faFacebook"
          }
      ],
      "_links": {
          "self": {
              "href": "/api/articles/d8c797c5-f06e-4b08-9570-b713ea9e2ed2"
          },
          "article": {
              "href": "/api/articles/d8c797c5-f06e-4b08-9570-b713ea9e2ed2"
          }
      }
  }
};

export const LONGTEXT = {
    args: {
        id: "id",
        title: 'Flott tittel',
        subtitle: "Lengre forklarende teskt om hva dette er for noe",
        text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        link: 'https://vg.no'
    }
};

export const NULLUNDERTITTEL     = {
    args: {
        id: "id",
        title: 'Flott tittel',
        text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        link: 'https://vg.no'
    }
};

export const NOLINK = {
    args: {
        id: "id",
        title: 'Flott tittel',
        text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    }
};
